import 'package:wanandroid_flutter/config/const.dart';
import 'package:wanandroid_flutter/config/service_url.dart';
import 'package:wanandroid_flutter/service/base_resp.dart';
import 'package:wanandroid_flutter/service/page_resp.dart';
import 'package:wanandroid_flutter/todos/model/todo.dart';
import 'package:wanandroid_flutter/todos/model/todo_status.dart';
import '../service/service_method.dart';

class TodosRepository {
  Future<List<Todo>> getTodos(TodoStatus todoStatus, int page) async {
    int status = TodoStatus.values.indexOf(todoStatus);

    var query = {'status': status};

    String path =
        WanAndroidApi.getPath(path: WanAndroidApi.TODO_LIST, page: page);


    print('请求 path:  $path');

    BaseResp<Map<String, dynamic>> baseResp =
        await request(path, method: Method.get, formData: query);

    if (baseResp.code != status_success) {
      return Future.error(baseResp.msg);
    }

    List<Todo> list = null;
    if (baseResp.data != null) {
      PageResp pageResp = PageResp.fromJson(baseResp.data);
      print(pageResp);

      list = pageResp.datas?.map((value) {
        Todo todo = Todo.fromJson(value);
        return todo;
      })?.toList();
    }

    print(list);
    return list;
  }
}
