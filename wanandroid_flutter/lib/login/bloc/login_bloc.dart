import 'package:wanandroid_flutter/login/bloc/index.dart';

import 'package:bloc/bloc.dart';
import 'package:rxdart/rxdart.dart';
import 'package:wanandroid_flutter/util/validators.dart';

import '../login_repository.dart';

class LoginBloc extends Bloc<LoginEvent, LoginState> {
  @override
  LoginState get initialState => LoginState.empty();

  @override
  Stream<LoginState> transformEvents(Stream<LoginEvent> events,
      Stream<LoginState> Function(LoginEvent event) next) {
    //转换成  rxdart  observable
    final observableSteam = events as Observable<LoginEvent>;

    final nonDobunceSteam = observableSteam.where((event) {
      return (events is! NameChange && events is! PasswordChange);
    });
    // 输入事件 需要间隔监听
    final dobunceSteam = observableSteam.where((event) {
      return (events is NameChange && events is PasswordChange);
    }).debounceTime(Duration(milliseconds: 300));

    return super
        .transformEvents(nonDobunceSteam.mergeWith([dobunceSteam]), next);
  }

  @override
  Stream<LoginState> mapEventToState(LoginEvent event) async* {
    if (event is NameChange) {
      yield* _mapNameChangeToState(event.name);
    } else if (event is PasswordChange) {
      yield* _mapPasswordChangeToState(event.password);
    } else if (event is LoginPressed) {
      yield* _mapLoginPressedToState(event.name, event.password);
    }
  }

  Stream<LoginState> _mapNameChangeToState(String name) async* {
    yield currentState.update(isNamelValid: Validators.isValidName(name));
  }

  Stream<LoginState> _mapPasswordChangeToState(String password) async* {
    yield currentState.update(
        isPasswordValid: Validators.isValidPassword(password));
  }

  Stream<LoginState> _mapLoginPressedToState(
      String name, String password) async* {
    yield LoginState.loading();
    try {
      await new LoginRepository().login(name, password);
      print('bloc  Login success' );
      yield LoginState.success();
    } catch (_) {
      yield LoginState.fail();
    }
  }
}
