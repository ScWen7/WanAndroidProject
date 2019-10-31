
import 'package:equatable/equatable.dart';
import 'package:wanandroid_flutter/todos/model/todo.dart';

abstract class TodoEvent extends Equatable{}


class LoadTodos extends TodoEvent{}


class AddTodo extends TodoEvent{


  final Todo todo;

  AddTodo({this.todo});

}


class UpdateTodo extends TodoEvent{
  final Todo todo;

  UpdateTodo({this.todo});
}


class DeleteTodo extends TodoEvent{
  final Todo todo;

  DeleteTodo({this.todo});
}

