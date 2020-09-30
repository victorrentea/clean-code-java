//import java.net.URL;
//
//@Override
//    public Optional<getResponseData> getResponse(String a, String b, C c) {
//		return getResponse(a,b,c, false);
//    }
//
//  @Override
//  public Optional<getResponseData> getResponseWithLabel(String a, UrlBuilder  urlBuilder){
//		return getResponse(a,b,c, true);
//		 }
//
//  @Override
//  public Optional<getResponseData> getResponseWithLabel(String a, String b, C c){
//		return getResponse(a,b,c, true);
//		 }
//  @Override
//  private Optional<getResponseData> getResponse(String a, String b, C c, boolean isIncludeLabel) {
//	  ....
//	  ....
//	   String url = "/xxx/yyyy/?bucket=1";
//		if (isIncludeLabel) {
//			url = url + "&label=1";
//		}
//		if (isStuff) {
//			url = url + "&label=1";
//		}
//		if (isFluff) {
//			url = url + "&label=1";
//		}
//		if (isIncludeLabel) {
//			url = url + "&label=1";
//		}
//		callWS(url)
//    }
//	  }
//
//	  class UrlBuilder {
//		  String url = "default url";
//		  List<String> parts = new
//
//		  public UrlBuilder includeLabel() {
//			  parts.add("label=1");
//		  }
//		  public UrlBuilder includeLabel() {
//			  url = url + "&label=1";
//		  }
//		  URL build() {
//		  	return url + "?" +  parts.stream().collect joining ("&")
//		  }
//	  }