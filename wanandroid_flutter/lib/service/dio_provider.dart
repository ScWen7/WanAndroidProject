import 'package:dio/dio.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';
import 'package:cookie_jar/cookie_jar.dart';

class DioProvider {
  static Dio _instance;

  DioProvider._();

  static Dio getInstance() {
    if (_instance == null) {
      _instance = Dio();
      _instance.options.contentType = "application/x-www-form-urlencoded";
      _instance.interceptors.add(LogInterceptor());
      _instance.interceptors.add(CookieManager(CookieJar()));
    }
    return _instance;
  }
}
