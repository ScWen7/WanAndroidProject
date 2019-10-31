
import 'package:equatable/equatable.dart';
import 'package:wanandroid_flutter/todos/model/todo.dart';

abstract class  TodoState extends Equatable{}


class TodosLoading extends TodoState{}


class TodosLoaded extends TodoState{
  final List<Todo> todos;

  TodosLoaded({this.todos});

}


