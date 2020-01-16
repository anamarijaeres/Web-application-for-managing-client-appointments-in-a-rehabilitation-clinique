import React, { Component } from 'react';
import Navbar from './components/Navbar'
import {BrowserRouter, Route, Switch} from 'react-router-dom'
import Home from './components/Home.js'
import Login from './components/Login.js'
import Signup from './components/Signup.js';
import UserLogin from './components/UserLogin.js';
import DoctorCoachList from "./components/DoctorCoachList";
import EditProfile from './components/EditProfile.js'
import DoctorProfile from "./components/DoctorProfile";
import Review from "./components/Review";
import Reply from "./components/Reply";
import AddProductCategory from './components/AddProductCategory.js'
import AddProduct from './components/AddProduct.js'
import SetTask from './components/SetTask.js';
import AddExercise from './components/AddExercise.js'
import ConsumedProduct from "./components/ConsumedProduct";
import Statistic from "./components/Statistic";
import CoachProfile from './components/CoachProfile.js';
import TrainingStatistics from './components/TrainingStatistics.js';
import LoadExercises from './components/LoadExercises.js'
import EditExercise from './components/EditExercise.js'
import EditExerciseForm from './components/EditExerciseForm.js'




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
            <Route path={'/:username'+'/addProduct'}  component={(match)=><ConsumedProduct matchLink={match} />}/>
            <Route path={'/:username'+'/statistic'}  component={(match)=><Statistic matchLink={match} />}/>
          <Route path='/addProduct' component={AddProduct}/>
          <Route path='/addProductCategory' component={AddProductCategory}/>
          <Route path='/login' component={()=><Login handleLogin={this.handleLogin} />}/>
          <Route path='/signup' component={Signup}/>
          <Route exact path='/' component={Home}/>
          <Route path='/review'  component={Review}/>
          <Route path='/editExercise' component={EditExercise}/>
          <Route path={'/editExerciseForm' + '/:name'} component={(match)=><EditExerciseForm matchLink={match}/>}/>
          <Route path={'/loadExercises' + '/:id'}  component={(match)=><LoadExercises matchLink={match}/>}/>
          <Route path={'/reply'+ '/:username' +'/:id'+'/:us'}  component={(match)=><Reply matchLink={match}/>}/>
          <Route path={'/profile'+ '/:username' } component={(match)=><DoctorProfile matchLink={match}/>}/>
          <Route path={'/coach'+ '/:username' } component={(match)=><CoachProfile matchLink={match}/>}/>
          <Route path={'/:username'+'/setTask/'+':clientUsername'} component={(match)=><SetTask matchLink={match} />}/>
          <Route path={'/:username'+'/addExercise'} component={AddExercise}/>
          <Route path={'/:username'+'/editProfile'} component={EditProfile}/>
          <Route path= {'/:username' + '/trainings'} component = {(match)=><TrainingStatistics matchLink={match}/>}/>
          <Route path='/:username' component={(match)=><UserLogin matchLink={match} />}/>
          </Switch>
        </div>
    </BrowserRouter>
    );
  }
}

export default App;