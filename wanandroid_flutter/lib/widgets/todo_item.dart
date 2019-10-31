import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:wanandroid_flutter/todos/model/todo.dart';

class TodoItem extends StatelessWidget {
  final Todo todo;

  TodoItem({this.todo});

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(todo.content),
    );
  }
}
