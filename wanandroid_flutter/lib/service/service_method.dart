import 'package:wanandroid_flutter/service/base_resp.dart';

import 'dio_provider.dart';
import 'package:dio/dio.dart';
import 'dart:convert';

/// BaseResp [String status]字段 key, 默认：status.
String _statusKey = "status";

/// BaseResp [int code]字段 key, 默认：errorCode.
String _codeKey = "errorCode";

/// BaseResp [String msg]字段 key, 默认：errorMsg.
String _msgKey = "errorMsg";

/// BaseResp [T data]字段 key, 默认：data.
String _dataKey = "data";

enum Method { get, post }

Future<BaseResp<T>> request<T>(url, {Method method, formData}) async {
  Response response;
  final dio = await DioProvider.getInstance();

  if (method == Method.get) {
    if (formData == null) {
      response = await dio.get(url);
    } else {
      response = await dio.get(url, queryParameters: formData);
    }
  } else {
    if (formData == null) {
      response = await dio.post(url);
    } else {
      response = await dio.post(url, data: formData);
    }
  }

  String _status;
  int _code;
  String _msg;
  T _data;
  if (response.statusCode == 200 || response.statusCode == 201) {
    try {
      if (response.data is Map) {
        _status = (response.data[_statusKey] is int)
            ? response.data[_statusKey].toString()
            : response.data[_statusKey];
        _code = (response.data[_codeKey] is String)
            ? int.tryParse(response.data[_codeKey])
            : response.data[_codeKey];
        _msg = response.data[_msgKey];
        _data = response.data[_dataKey];
      } else {
        Map<String, dynamic> _dataMap = _decodeData(response);
        _status = (_dataMap[_statusKey] is int)
            ? _dataMap[_statusKey].toString()
            : _dataMap[_statusKey];
        _code = (_dataMap[_codeKey] is String)
            ? int.tryParse(_dataMap[_codeKey])
            : _dataMap[_codeKey];
        _msg = _dataMap[_msgKey];
        _data = _dataMap[_dataKey];
      }
      return new BaseResp(_status, _code, _msg, _data);
    } catch (e) {
      return new Future.error(new DioError(
        response: response,
        error: "data parsing exception...",
        type: DioErrorType.RESPONSE,
      ));
    }
  }

  return new Future.error(new DioError(
    response: response,
    error: "statusCode: $response.statusCode, service error",
    type: DioErrorType.RESPONSE,
  ));
}

/// decode response data.
Map<String, dynamic> _decodeData(Response response) {
  if (response == null ||
      response.data == null ||
      response.data.toString().isEmpty) {
    return new Map();
  }
  return json.decode(response.data.toString());
}
