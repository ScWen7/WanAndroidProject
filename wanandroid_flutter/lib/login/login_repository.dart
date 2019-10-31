import '../service/service_method.dart';
import '../config/service_url.dart';

class LoginRepository {
  Future login(String name, String password) {
    var formData = {'username': name, 'password': password};
    return request(WanAndroidApi.LOGIN_URL, formData: formData);
  }

}
