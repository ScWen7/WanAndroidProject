import 'package:bloc/bloc.dart';
import 'package:wanandroid_flutter/todos/bloc/todo_event.dart';
import 'package:wanandroid_flutter/todos/bloc/todo_state.dart';
import 'package:wanandroid_flutter/todos/model/todo.dart';
import 'package:wanandroid_flutter/todos/model/todo_status.dart';
import 'package:wanandroid_flutter/todos/todos_repository.dart';

class TodosBloc extends Bloc<TodoEvent, TodoState> {
  @override
  TodoState get initialState => TodosLoading();

  @override
  Stream<TodoState> mapEventToState(TodoEvent event) async* {
    if (event is LoadTodos) {
      yield* _mapLoadTodoToState();
    }
  }

  Stream<TodoState> _mapLoadTodoToState() async* {
    List<Todo> list =
        await TodosRepository().getTodos(TodoStatus.complated, 1);

    yield TodosLoaded(todos: list);
  }
}
