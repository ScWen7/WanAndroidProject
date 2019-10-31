import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:wanandroid_flutter/todos/bloc/tab_bloc.dart';
import 'package:wanandroid_flutter/todos/bloc/tab_event.dart';
import 'package:wanandroid_flutter/todos/bloc/todo_event.dart';
import 'package:wanandroid_flutter/todos/bloc/todos_bloc.dart';
import 'package:wanandroid_flutter/todos/todo_list.dart';
import 'package:wanandroid_flutter/widgets/tab_selector.dart';

import 'model/app_tab.dart';

class TodosScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
        providers: [
          BlocProvider<TabBloc>(
            builder: (context) => TabBloc(),
          ),
          BlocProvider<TodosBloc>(
            builder: (context) => TodosBloc()..dispatch(LoadTodos()),
          )
        ],
        child: BlocBuilder<TabBloc, AppTab>(
          builder: (context, tab) {
            final tabBloc = BlocProvider.of<TabBloc>(context);
            return Scaffold(
              appBar: AppBar(
                centerTitle: true,
                title: Text('待办事项'),
              ),
              body: TodoList(activeTab: tab),
              floatingActionButton: FloatingActionButton(
                onPressed: (){},
                child: Icon(Icons.add),
              ),
              bottomNavigationBar: TabSelector(
                activeTab: tab,
                onTabSelected: (curTab) =>
                    tabBloc.dispatch(UpdateTab(tab: curTab)),
              ),
            );
          },
        ));
  }
}
