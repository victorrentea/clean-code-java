//package victor.training.cleancode
//
//// You don't have to read this line by line :-)
//// Don't:
//fun map(dto: OrderDTO, authData: RequestAuthData) = OrderEntity(
//    id = dto.id,
//    shopId = getShopId(dto),
//    batchState = BatchState.RECEIVED,
//    orderData = mapOrderData(dto),
//    partnerUserId = authData.sessionOwnerId ?: restExc("No sessionId supplied", 401),
//    apiKey = authData.apiKey,
//    dateCreated = if (dto.dateCreated != null) dto.dateCreated else Instant.now(),
//)
//
//private fun mapOrderData(dto: OrderDTO) {
//    OrderDataEntity(
//        orderItems = dto.orderItems.map { dto -> mapToEntity(dto) },
//        shippingType = dto.shipping.shippingType.id,
//        address = mapToEntity(dto.shipping.address),
//        correlationOrderId = dto.correlation?.partner?.orderId,
//        externalInvoiceData = dto.externalInvoiceData?.let {
//            ExternalInvoiceDataEntity(
//                url = it.url,
//                total = it.total,
//                currencyId = it.currency.id
//            )
//        }
//    )
//}
//
//private fun getShopId(dto: OrderDTO) = try {
//    extractItemIds(dto.orderItems[0].element.href).shopId
//} catch (e: BatchOrderProcessingException) {
//    restExc("Couldn't retrieve shop id from first order item: ${e.msg}")
//}