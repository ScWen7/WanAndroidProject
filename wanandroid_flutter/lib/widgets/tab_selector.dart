import 'package:flutter/material.dart';
import 'package:wanandroid_flutter/todos/model/app_tab.dart';

class TabSelector extends StatelessWidget {
  final AppTab activeTab;
  final Function(AppTab) onTabSelected;

  TabSelector({Key key, @required this.activeTab, @required this.onTabSelected})
      : super(key: key);

  @override
  Widget build(BuildContext context) {

    return BottomNavigationBar(
      currentIndex: AppTab.values.indexOf(activeTab),
      onTap: (index)=> onTabSelected(AppTab.values[index]),
      items: AppTab.values.map((tab){
        return BottomNavigationBarItem(
          icon: Icon(tab == AppTab.incompleted?Icons.list:Icons.check_box),
          title: Text(
            tab == AppTab.incompleted?'待办':'已完成'
          )
        );
      }).toList(),
    );
  }
}
