class WanAndroidApi {
  static String serviceUrl = 'https://www.wanandroid.com/';

  static String LOGIN_URL = '$serviceUrl user/login';

  static String TODO_LIST = '$serviceUrl lg/todo/v2/list';

  static String getPath({String path: '', int page, String resType: 'json'}) {
    StringBuffer sb = new StringBuffer(path);
    if (page != null) {
      sb.write('/$page');
    }
    if (resType != null && resType.isNotEmpty) {
      sb.write('/$resType');
    }
    return sb.toString();
  }
}
