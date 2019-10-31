import 'package:bloc/bloc.dart';
import 'package:wanandroid_flutter/todos/model/app_tab.dart';
import 'package:wanandroid_flutter/todos/bloc/tab_event.dart';

class TabBloc extends Bloc<TabEvent, AppTab> {
  @override
  AppTab get initialState => AppTab.incompleted;

  @override
  Stream<AppTab> mapEventToState(TabEvent event) async* {
    if (event is UpdateTab) {
      yield event.tab;
    }
  }
}
