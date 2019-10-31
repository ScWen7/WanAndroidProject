import 'package:flustars/flustars.dart';
import 'package:wanandroid_flutter/config/const.dart';

class Util{


  static bool isLogin(){
    var isLogin =  SpUtil.getBool(IS_LOGIN,defValue: false);

    print(isLogin);

    return isLogin;
  }

}