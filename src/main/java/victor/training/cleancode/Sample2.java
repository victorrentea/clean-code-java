//package victor.training.cleancode;
///**************************************************************
// *
// *  SWIFT (c) 2025. All rights reserved.
// *
// *  This software and its associated documentation contain
// *  proprietary, confidential and trade secret information of
// *  S.W.I.F.T. SC, and except as provided in your contractual
// *  arrangements with S.W.I.F.T. SC.
// *  a) no part may be reproduced, made available,
// *     adapted or translated in any form or by any means, and
// *  b) this software may only be installed and used by a duly
// *     registered SWIFT User, SWIFT Partner or Service Bureau
// *     in compliance with the terms of the licence with SWIFT.
// *     This software may only be used to access and use the
// *     SWIFT services and products.
// *  For more information about what you may or may not do with
// *  this software and any related documentation, including any
// *  limitations on warranties and/or remedies, refer to your
// *  relevant contractual arrangements with SWIFT.
// ****************************************************************/
//package com.swift.cloud.ni.beeline.swiftnet.pki;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.CompletionException;
//import java.util.stream.Collectors;
//
//import com.swift.cloud.lite.backend.nicontroller.event.fromswift.SwGblStatusAttribute;
//import com.swift.cloud.ni.beeline.pki.client.VerifyServiceClient;
//import com.swift.cloud.ni.beeline.pki.verify.v1.CheckCRLValues;
//import com.swift.cloud.ni.beeline.pki.verify.v1.VerifyE2ERequest;
//import com.swift.cloud.ni.beeline.swiftnet.api.pki.SwiftNetPrimitiveSignature;
//import com.swift.cloud.ni.beeline.swiftnet.pki.error.SwiftNetPkiGrpcExceptionMapper;
//import com.swift.cloud.ni.beeline.swiftnet.api.pki.signaturelist.SignatureReference;
//import com.swift.cloud.ni.beeline.swiftnet.api.pki.signaturelist.SignedSignature;
//import com.swift.cloud.ni.beeline.swiftnet.pki.signaturelist.mapper.SignatureXmlPrimitiveMapper;
//import com.swift.cloud.ni.beeline.swiftnet.primitive.error.EnhancedError;
//import com.swift.cloud.ni.beeline.swiftnet.primitive.error.SwGblStatusException;
//import com.swift.cloud.ni.beeline.swiftnet.primitive.marshalling.SwiftNetPrimitiveMarshaller;
//import com.swift.cloud.ni.beeline.swiftnet.primitive.marshalling.SwiftNetPrimitiveMarshallingException;
//import com.swift.common.config.text.tracer.Tracer;
//import com.swift.common.config.text.tracer.TracerFactory;
//import com.swift.common.mx.DNUtils;
//import com.swift.platform.cloud.app.event.DeploymentType;
//import com.swift.platform.cloud.app.event.ISystemSharedConfiguration;
//import com.swift.rocket.lib.swiftnet.digest.SwiftNetDigestService;
//import com.swift.rocket.lib.swiftnet.digest.model.SignatureDigestsVerificationResult;
//import com.swift.rocket.lib.swiftnet.digest.model.SignatureListDigestVerificationResults;
//import com.swift.rocket.lib.swiftnet.digest.model.SwReference;
//import com.swift.rocket.lib.swiftnet.digest.model.SwSecSignature;
//import com.swift.rocket.lib.swiftnet.digest.model.SwSecSignatureList;
//import com.swift.rocket.lib.swiftnet.digest.model.reference.DigestReference;
//import io.grpc.Status;
//import io.grpc.StatusRuntimeException;
//import jakarta.inject.Inject;
//import jakarta.inject.Singleton;
//
//@Singleton // Guice
//public class SwiftNetPkiVerifier {
//  private static final Tracer tracer = TracerFactory.getTracer(SwiftNetPkiVerifier.class);
//
//  private static final String VERIFY_SERVICE_INVALID_SIGNATURE_ERROR = "Signature verification E2E failed";
//
//  private final ISystemSharedConfiguration systemSharedConfiguration;
//  private final SwiftNetPkiConfiguration configuration;
//  private final SwiftNetPkiMessages messages;
//  private final SwiftNetDigestService swiftNetDigestService;
//  private final VerifyServiceClient verifyServiceClient;
//  private final SwiftNetPrimitiveMarshaller marshaller;
//  private final SwiftNetPkiGrpcExceptionMapper exceptionMapper;
//
//  private final Set<String> liveEnvironmentPolicyOids;
//  private final Set<String> testEnvironmentPolicyOids;
//
//  @Inject
//  public SwiftNetPkiVerifier(
//      ISystemSharedConfiguration systemSharedConfiguration,
//      SwiftNetPkiConfiguration configuration,
//      SwiftNetPkiMessages messages,
//      SwiftNetDigestService swiftNetDigestService,
//      VerifyServiceClient verifyServiceClient,
//      SwiftNetPrimitiveMarshaller marshaller,
//      SwiftNetPkiGrpcExceptionMapper exceptionMapper) {
//    this.systemSharedConfiguration = systemSharedConfiguration;
//    this.configuration = configuration;
//    this.messages = messages;
//    this.swiftNetDigestService = swiftNetDigestService;
//    this.verifyServiceClient = verifyServiceClient;
//    this.marshaller = marshaller;
//    this.exceptionMapper = exceptionMapper;
//
//    this.liveEnvironmentPolicyOids = Set.of(configuration.getLiveEnvironmentPolicyOids());
//    this.testEnvironmentPolicyOids = Set.of(configuration.getTestEnvironmentPolicyOids());
//  }
//
//  /**
//   * Verify the signature of a SwiftNet primitive.
//   *
//   * @param primitiveXml the XML representation of the SwiftNet primitive
//   * @param signature the SwiftNet primitive signature (can be either a signature or a crypto block)
//   * @param mandatoryReferences the mandatory references for that message (or member refs for crypto blocks)
//   * @param requestorDn the requestor DN of the XML primitive
//   * @return the signature verification result
//   */
//  public CompletableFuture<SignatureVerificationResult> verifyPrimitiveSignature(String primitiveXml, SwiftNetPrimitiveSignature signature, List<String> mandatoryReferences, String requestorDn) {
//    if (signature.getSignature() == null && signature.getCryptoBlock() == null) {
//      return CompletableFuture.failedFuture(new IllegalArgumentException("Signature or CryptoBlock cannot be null"));
//    }
//
//    boolean policyOIDValid = validateSignaturePolicyOID(signature);
//    if (!policyOIDValid) {
//      return CompletableFuture.completedFuture(SignatureVerificationResult.INVALID_CERT_POLICY_ID);
//    }
//
//    boolean signerDnValid = validateSignerDn(requestorDn, signature);
//    if (!signerDnValid) {
//      return CompletableFuture.completedFuture(SignatureVerificationResult.INVALID_SIGNER_DN);
//    }
//
//    boolean digestValid = validateDigest(primitiveXml, signature, mandatoryReferences);
//    if (!digestValid) {
//      return CompletableFuture.completedFuture(SignatureVerificationResult.INVALID_DIGEST);
//    }
//
//    return validateSignature(signature);
//  }
//
//
//
//  private CompletableFuture<SignatureVerificationResult> validateSignature(SwiftNetPrimitiveSignature signature) {
//    if (signature.getSignature() != null) {
//      return validateSignatureSignature(signature.getSignature());
//    } else if (signature.getCryptoBlock() != null) {
//      throw new IllegalArgumentException("CryptoBlock cannot be null");
//    } else {
//      throw new IllegalArgumentException("Signature or CryptoBlock cannot be null");
//    }
//  }
//
//  private CompletableFuture<SignatureVerificationResult> validateSignatureSignature(Signature signature) {
//    VerifyE2ERequest request;
//    try {
//      String xmlSignature = marshaller.marshallToString(SignatureXmlPrimitiveMapper.mapToXmlPrimitive(signature));
//      request = VerifyE2ERequest.newBuilder()
//          .setSignature(xmlSignature)
//          .setCRL(CheckCRLValues.CHECK_CRL)
//          .build();
//    } catch (SwiftNetPrimitiveMarshallingException e) {
//      messages.marshallSignatureError(e);
//      throw SwGblStatusException.locallyRejected(SwGblStatusAttribute.fatalErrorBuilder(EnhancedError.INTERNAL_ERROR.getCode())
//          .text("Error while verifying signature")
//          .action(EnhancedError.ACTION_CONTACT_SUPPORT)
//          .build());
//    }
//
//    // catch(StatusRuntimeException statusRuntimeException) {
//    //if (statusRuntimeException.getStatus().getCode() == Status.Code.INVALID_ARGUMENT
//    //              && statusRuntimeException.getMessage().contains(VERIFY_SERVICE_INVALID_SIGNATURE_ERROR)) {
//    //            messages.invalidSignature(statusRuntimeException.getMessage());
//    //            return SignatureVerificationResult.INVALID_SIGNATURE;
//    //          }
//  // }
//
//    return verifyServiceClient.verifyEndToEnd(request).handle((response, ex) -> {
//      if (ex == null) {
//        // If the verify service doesn't return an error, the signature is considered valid.
//        return SignatureVerificationResult.SUCCESS;
//      }
//
//      if (ex instanceof CompletionException completionEx) {
//        ex = completionEx.getCause();
//      }
//
//      if (ex instanceof StatusRuntimeException statusRuntimeException) {
//        if (statusRuntimeException.getStatus().getCode() == Status.Code.INVALID_ARGUMENT
//            && statusRuntimeException.getMessage().contains(VERIFY_SERVICE_INVALID_SIGNATURE_ERROR)) {
//          messages.invalidSignature(statusRuntimeException.getMessage());
//          return SignatureVerificationResult.INVALID_SIGNATURE;
//        }
//      }
//
//      throw exceptionMapper.map(ex);
//
//    });
//  }
//
//  private boolean validateSignaturePolicyOID(SwiftNetPrimitiveSignature signature) {
//    String policyOid = getPolicyOid(signature);
//
//    if (isProcessedByTransactionManager(signature)) {
//      if (!configuration.getTransactionManagerPolicyOid().equals(policyOid)) {
//        messages.invalidTmPolicyOid(policyOid);
//        return false;
//      }
//
//      return true;
//    }
//
//    if (isProcessedByInFlowTransaction(signature)) {
//      // If message is processed by in-flow translation, the certificate policy OID must not be validated.
//      return true;
//    }
//
//    if (systemSharedConfiguration.getDeploymentType()== DeploymentType.LIVE && !liveEnvironmentPolicyOids.contains(policyOid)) {
//      messages.invalidLiveEnvironmentPolicyOid(policyOid);
//      return false;
//    } else if (systemSharedConfiguration.getDeploymentType()== DeploymentType.TEST && !testEnvironmentPolicyOids.contains(policyOid)) {
//      messages.invalidTestEnvironmentPolicyOid(policyOid); // business outcome for a non-dev
////      tracer.finest("Sauvignon")//technical log
//      return false;
//    }
//
//    return true;
//  }
//
//  private String getPolicyOid(SwiftNetPrimitiveSignature signature) {
//    if (signature.getSignature() != null) {
//      return signature.getSignature().keyInfo().certPolicyId();
//    } else if (signature.getCryptoBlock() != null) {
//      return signature.getCryptoBlock().cryptoDescriptor().certPolicyId();
//    } else {
//      throw new IllegalArgumentException("Signature or CryptoBlock cannot be null");
//    }
//  }
//
//  private boolean validateSignerDn(String requestorDn, SwiftNetPrimitiveSignature signature) {
//    String signerDn;
//    if (signature.getSignature() != null) {
//      signerDn = signature.getSignature().keyInfo().signDn();
//    } else if (signature.getCryptoBlock() != null) {
//      signerDn = signature.getCryptoBlock().cryptoDescriptor().signDn();
//    } else {
//      throw new IllegalArgumentException("Signature or CryptoBlock cannot be null");
//    }
//
//    requestorDn = DNUtils.canonicalize(requestorDn);
//    signerDn = DNUtils.canonicalize(signerDn);
//
//    if (isProcessedByTransactionManager(signature)) {
//      if (!signerDn.endsWith(configuration.getTransactionManagerSignerDnSuffix())) {
//        messages.invalidTmSignerDn(signerDn);
//        return false;
//      }
//
//      tracer.finest("Message is processed by Transaction Manager and signer DN is valid");
//      return true;
//    }
//
//    if (isProcessedByInFlowTransaction(signature)) {
//      if (!signerDn.endsWith(configuration.getInFlowTranslationSignerDnSuffix())) {
//        messages.invalidInFlowTranslationSignerDn(signerDn);
//        return false;
//      }
//
//      tracer.finest("Message is processed by in-flow translation and signer DN is valid");
//      return true;
//    }
//
//    // If the 2 last fragments of the sender DN and signer DN are not equal, otherwise, it means the
//    // sender of the message is not the signer which is not allowed.
//    if (!Objects.equals(DNUtils.getAncestor(requestorDn, 2), DNUtils.getAncestor(signerDn, 2))) {
//      messages.invalidSignerDn(signerDn, requestorDn);
//      return false;
//    }
//
//    return true;
//  }
//
//  private boolean validateDigest(String primitiveXml, SwiftNetPrimitiveSignature signature, List<String> mandatoryReferences) {
//    if (signature.getSignature() != null) {
//      return validateDigestBySignature(primitiveXml, signature.getSignature(), mandatoryReferences);
//    } else if (signature.getCryptoBlock() != null) {
//      throw new UnsupportedOperationException("CryptoBlock not supported");
//    } else {
//      throw new IllegalArgumentException("Signature or CryptoBlock cannot be null");
//    }
//  }
//
//  private boolean validateDigestBySignature(String primitiveXml, Signature signature, List<String> mandatoryReferences) {
//    List<String> missingReferences = getMissingReferences(signature, mandatoryReferences);
//    if (!missingReferences.isEmpty()) {
//      messages.mandatoryReferencesMissing(String.join(", ", missingReferences));
//      return false;
//    }
//
//    SwSecSignatureList signatureList = mapToSwSecSignatureList(signature);
//
//    List<String> partialCopyPaths = null; // TODO victorrentea 2025-09-29 JIRA1312 : propage this info correctly for partial copy if applicable.
//    SignatureListDigestVerificationResults result = swiftNetDigestService.verifySignatureListDigests(primitiveXml, signatureList, partialCopyPaths);
//    if (!result.isVerificationSuccess()) {
//      messages.invalidDigest(formatDigestServiceResult(result));
//      return false;
//    } else {
//      tracer.fine("Digest verification successful:\n{}", formatDigestServiceResult(result));
//    }
//
//    return true;
//  }
//
//  private List<String> getMissingReferences(Signature signature, List<String> mandatoryReferences) {
////    Set<String> signatureReferences = signature.toSignatureReferences();
//    Set<String> signatureReferences = signature
//        .manifest()
//        .references()
//        .stream()
//        .map(SignatureReference::digestReference)
//        .collect(Collectors.toSet());
//
//    return mandatoryReferences.stream()
//        .filter(reference -> !signatureReferences.contains(reference))
//        .toList();
//  }
//
//  private boolean isProcessedByTransactionManager(SwiftNetPrimitiveSignature signature) {
//    if (signature.getCryptoBlock() != null) {
//      // If the message has been signed with CryptoBlock, it is not possible that it has been processed by the transaction manager.
//      return false;
//    }
//
//    return signature.getSignature()
//        .manifest()
//        .references()
//        .stream()
//        .anyMatch(reference -> DigestReference.TRD.getDigestName().equals(reference.digestReference()));
//  }
//
//  private boolean isProcessedByInFlowTransaction(SwiftNetPrimitiveSignature signature) {
//    if (signature.getCryptoBlock() != null) {
//      // If the message has been signed with CryptoBlock, it is not possible that it has been processed by the in-flow translation.
//      return false;
//    }
//
//    // return signature.hasTranslatedDigest();
//    return signature.getSignature()
//        .manifest()
//        .references()
//        .stream()
//        .anyMatch(reference -> DigestReference.TRANSLATED_MT.getDigestName().equals(reference.digestReference()));
//  }
//
//  private SwSecSignatureList mapToSwSecSignatureList(SignedSignature signature) {
//    List<SwReference> references = signature.manifest()
//        .references()
//        .stream()
//        .map(ref -> new SwReference(ref.digestReference(), ref.digestAlgorithm(), ref.digestValue()))
//        .toList();
//    String rnd = signature.object() != null ? signature.object().rnd() : null;
//    SwSecSignature swSecSignature = new SwSecSignature(references, rnd);
//    return new SwSecSignatureList(List.of(swSecSignature));
//  }
//
//  private String formatDigestServiceResult(SignatureListDigestVerificationResults result) {
//    StringBuilder sb = new StringBuilder();
//    for (int i = 0; i < result.getSignatureVerificationResults().size(); i++) {
//      SignatureDigestsVerificationResult signatureResult = result.getSignatureVerificationResults().get(i);
//      if (signatureResult.isSignatureVerificationSuccess()) {
//        sb.append("Signature entry ").append(i).append(" is valid\n");
//        continue;
//      }
//
//      sb.append("Signature entry ").append(i).append(" is invalid:\n");
//      for (SignatureDigestsVerificationResult.DigestVerificationResult digestResult: signatureResult.getDigestVerificationResults()) {
//        String statusMessage = switch (digestResult.getStatus()) {
//          case SUCCESS -> "valid";
//          case FAILED -> "invalid. Expected: " + digestResult.getDigestValueComputed() + ", got: " + digestResult.getOriginalDigestValue();
//          case SKIPPED -> "skipped";
//        };
//        sb.append("\tDigest ").append(digestResult.getDigestName()).append(" is ").append(statusMessage).append("\n");
//      }
//    }
//
//    return sb.toString();
//  }
//}
