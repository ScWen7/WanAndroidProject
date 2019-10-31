

import 'package:equatable/equatable.dart';
import 'package:wanandroid_flutter/todos/model/app_tab.dart';

abstract class TabEvent extends Equatable{

}


class UpdateTab extends TabEvent{
  final AppTab tab;

  UpdateTab({this.tab});

}

