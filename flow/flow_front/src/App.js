import React, { Component } from 'react';
import Navbar from './components/Navbar'
import {BrowserRouter, Route, Switch} from 'react-router-dom'
import Home from './components/Home.js'
import Login from './components/Login.js'
import Signup from './components/Signup.js';
import UserLogin from './components/UserLogin.js';

class App extends Component{
  //U LOCAL STORAGE SVI TIPOVI PODATAKA SE SPREMAJU KAO STRINGOVI
  constructor(props){
    super(props)
    
    if(sessionStorage.getItem('flag')===null){
      localStorage.setItem('userName', '');  
      localStorage.setItem('loggedIn', false);
      localStorage.setItem('role', '');
      sessionStorage.setItem('flag', 'set');
    }
  }

  // state={
  //   userName: '',
  //   loggedIn: false,
  //   role: ''
  // }


  handleLogin=(userName, status, role)=>{
    // this.setState({
    //   userName:userName,
    //   loggedIn:status,
    //   role:role
    // })

    localStorage.setItem('userName', userName);
    localStorage.setItem('loggedIn', status);
    localStorage.setItem('role', role);
    // this.setState({
    //   userName:userName,
    //   loggedIn:status,
    //   role:role
    // })
  }

  // componentDidMount(){
  //   const userName=localStorage.getItem('userName');
  //   const loggedIn=localStorage.getItem('loggedIn');
  //   const role=localStorage.getItem('role');
    //this.handleLogin(userName, loggedIn, role);
  //   this.setState({
  //     userName:userName,
  //     loggedIn:loggedIn,
  //     role:role
  //   })
  // }

  render(){
    return(
      <BrowserRouter>
        <div className="App">
          {/* <Navbar status={this.state.loggedIn}/> */}
          <Navbar/>
          <Switch>
          <Route path='/login' component={()=><Login handleLogin={this.handleLogin} />}/>
          <Route path='/signup' component={Signup}/>
          <Route exact path='/' component={Home}/>
          {/* <Route path='/:username' component={(match)=><UserLogin role={this.state.role} matchLink={match} username={this.state.userName}/>}/> */}
          <Route path='/:username' component={(match)=><UserLogin matchLink={match} />}/>
          </Switch>
        </div>
    </BrowserRouter>
    );
  }
}

export default App;