import 'dio_provider.dart';
import 'package:dio/dio.dart';

Future request(url, {formData}) async {
  Response response;
  if (formData == null) {
    response = await DioProvider.getInstance().post(url);
  } else {
    response = await DioProvider.getInstance().post(url, data: formData);
  }
  return response.data;
}


