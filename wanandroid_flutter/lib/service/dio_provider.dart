import 'dart:io';

import 'package:dio/dio.dart';
import 'package:dio_cookie_manager/dio_cookie_manager.dart';
import 'package:cookie_jar/cookie_jar.dart';
import 'package:path_provider/path_provider.dart';
class DioProvider {
  static Dio _instance;

  DioProvider._();

  static Future<Dio> getInstance() async {
    if (_instance == null) {
      _instance = Dio();
      _instance.options.contentType = "application/x-www-form-urlencoded";
      _instance.interceptors.add(LogInterceptor());
      Directory appDocDir = await getApplicationDocumentsDirectory();
      String appDocPath = appDocDir.path;
      var cookieJar=PersistCookieJar(dir:appDocPath+"/.cookies/");
      _instance.interceptors.add(CookieManager(cookieJar));
    }
    return _instance;
  }
}
