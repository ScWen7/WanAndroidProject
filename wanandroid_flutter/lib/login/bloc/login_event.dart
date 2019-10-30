import 'package:equatable/equatable.dart';
import 'package:flutter/cupertino.dart';

abstract class LoginEvent  extends Equatable{

}


class NameChange extends LoginEvent{
  final String name;

  NameChange({@required this.name});
}


class PasswordChange extends LoginEvent{

  final String password;

  PasswordChange({@required this.password});


}


class LoginPressed extends LoginEvent{
  final String name;
  final String password;

  LoginPressed({@required this.name,@required this.password});

}



