import React, { Component } from 'react';
import Navbar from './components/Navbar'
import {BrowserRouter, Route, Switch} from 'react-router-dom'
import Home from './components/Home.js'
import Login from './components/Login.js'
import Signup from './components/Signup.js';
import UserLogin from './components/UserLogin.js';
import DoctorCoachList from "./components/DoctorCoachList";
import EditProfile from './components/EditProfile.js'
import DoctorCoachProfile from "./components/DoctorCoachProfile";
import Review from "./components/Review";
import Reply from "./components/Reply";
import AddProductCategory from './components/AddProductCategory.js'



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

  handleLogin=(userName, status, role)=>{
    localStorage.setItem('userName', userName);
    localStorage.setItem('loggedIn', status);
    localStorage.setItem('role', role);
  }

  render(){
    return(
      <BrowserRouter>
        <div className="App">
          <Navbar/>
          <Switch>
          <Route path='/login' component={()=><Login handleLogin={this.handleLogin} />}/>
          <Route path='/signup' component={Signup}/>
          <Route exact path='/' component={Home}/>
          <Route path='/review'  component={Review}/>
          <Route path={'/reply'+ '/:username' +'/:id'+'/:us'}  component={(match)=><Reply matchLink={match}/>}/>
          <Route path={'/profile'+ '/:username' } component={(match)=><DoctorCoachProfile matchLink={match}/>}/>
          <Route path={'/:username'+'/editProfile'} component={EditProfile}/>
          <Route path='/:username' component={(match)=><UserLogin matchLink={match} />}/>
          <Route path='/addProductCategory' component={AddProductCategory}/>
          </Switch>
        </div>
    </BrowserRouter>
    );
  }
}

export default App;