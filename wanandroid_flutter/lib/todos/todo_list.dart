import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:wanandroid_flutter/todos/bloc/todo_state.dart';
import 'package:wanandroid_flutter/todos/bloc/todos_bloc.dart';
import 'package:wanandroid_flutter/todos/model/app_tab.dart';
import 'package:wanandroid_flutter/widgets/loading_indicator.dart';
import 'package:wanandroid_flutter/widgets/todo_item.dart';

class TodoList extends StatelessWidget {
  final AppTab activeTab;

  TodoList({Key key, @required this.activeTab});

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<TodosBloc,TodoState>(
      builder: (context,state){
         if(state is TodosLoading){
           return LoadingIndicator();
         }else if(state is TodosLoaded){
          final todos =  state.todos;
          print(todos);
           return ListView.builder(itemBuilder: (context,index){
              return TodoItem(todo: todos[index],);
           }
             ,itemCount: todos.length,);
         }else{
           return Container();
         }
      },
    );
  }
}
