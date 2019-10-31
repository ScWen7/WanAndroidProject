import 'package:flustars/flustars.dart';
import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:wanandroid_flutter/config/config.dart';
import 'package:wanandroid_flutter/config/const.dart';
import 'package:wanandroid_flutter/config/routes.dart';
import 'package:wanandroid_flutter/login/bloc/index.dart';

class LoginScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: BlocProvider<LoginBloc>(
        builder: (context) => LoginBloc(),
        child: LoginForm(),
      ),
    );
  }
}

class LoginForm extends StatefulWidget {
  @override
  _LoginFormState createState() {
    return _LoginFormState();
  }
}

class _LoginFormState extends State<LoginForm> {


  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _pwdController = TextEditingController();

  LoginBloc _loginBloc;

  //输入框   判空处理
  bool get isPopulated =>
      _nameController.text.isNotEmpty && _pwdController.text.isNotEmpty;


  @override
  void initState() {
    super.initState();
    _loginBloc = BlocProvider.of<LoginBloc>(context);
    _nameController.addListener(onNameChange);
    _pwdController.addListener(onPasswordChange);

  }


  @override
  Widget build(BuildContext context) {
    return BlocListener<LoginBloc, LoginState>(
      listener: (context, state) {
        if (state.isFail ){
          Scaffold.of(context)
              ..hideCurrentSnackBar()
              ..showSnackBar(SnackBar(
                content: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    Text('登录失败'),
                    Icon(Icons.error)
                  ],
                ),
                backgroundColor: Colors.red,
              ));
        }

        if(state.isSubmitting){
          Scaffold.of(context)
            ..hideCurrentSnackBar()
            ..showSnackBar(
              SnackBar(
                content: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text('登录中...'),
                    CircularProgressIndicator(),
                  ],
                ),
              ),
            );
        }

        if(state.isSuccess){
          SpUtil.putBool(IS_LOGIN, true);
          var isLogin =  SpUtil.getBool(IS_LOGIN,defValue: false);
          print(isLogin);
          Navigator.pushReplacementNamed(context,RouteConfig.TODOS_ROUTE);
        }

      },
      child: BlocBuilder<LoginBloc, LoginState>(
        builder: (context, state) {
          return Padding(
            padding: EdgeInsets.all(20),
            child: Form(
              child: ListView(
                children: <Widget>[
                  Padding(
                    padding: EdgeInsets.symmetric(vertical: 20),
                    child: Image.asset(AssetsImageConfig.Logo,
                      height: 200,),
                  ),
                  TextFormField(
                    controller: _nameController,
                    decoration: InputDecoration(
                        icon: Icon(Icons.account_circle),
                        labelText: '用户名'
                    ),
                    autovalidate: true,
                    autocorrect: false,
                    maxLength: 16,
                    validator: (_) {
                      return !state.isNameValid ? "用户名不合法" : null;
                    },
                  ),
                  TextFormField(
                    controller: _pwdController,
                    decoration: InputDecoration(
                        icon: Icon(Icons.lock),
                        labelText: '密码'
                    ),
                    maxLength: 16,
                    autovalidate: true,
                    autocorrect: false,
                    validator: (_) {
                      return !state.isPasswordValid ? "密码不合法" : null;
                    },
                  ),
                  Padding(
                    padding: EdgeInsets.symmetric(vertical: 30),
                    child: RaisedButton(
                      color: Colors.blue,
                      splashColor: Colors.grey,
                      highlightColor: Colors.blue[700],
                      colorBrightness: Brightness.dark,
                      child: Text('登录'),

                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(20)
                      ),
                      onPressed: isLoginButtonEnabled(state)
                          ? onFormSubmitted
                          : null,
                    ),
                  )
                ],
              ),
            ),
          );
        },
      ),
    );
  }

  void onNameChange() {
    _loginBloc.dispatch(NameChange(name: _nameController.text));
  }

  void onPasswordChange() {
    _loginBloc.dispatch(PasswordChange(password: _pwdController.text));
  }

  bool isLoginButtonEnabled(LoginState state) {
    return state.isFormValid && isPopulated && !state.isSubmitting;
  }

  void onFormSubmitted() {
    _loginBloc.dispatch(LoginPressed(name: _nameController.text,password: _pwdController.text));
  }
}
