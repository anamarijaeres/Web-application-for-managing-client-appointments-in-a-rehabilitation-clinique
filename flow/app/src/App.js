import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import StudentList from "./components/StudentList";
import StudentForm from "./components/StudentForm";
import Header from "./components/Header";
import Login from "./components/Login";
import ClientForm from "./components/ClientForm";
import './App.css';
import StaffForm from './components/StaffForm';
import RequestList from './components/RequestList';
import { Link } from "react-router-dom";
import './components/Login.css';
import Card from "./components/Card";


function App() {
  const [isLoggedIn, setIsLoggedIn] = React.useState(false);
  const [loadingUser, setLoadingUser] = React.useState(true);

  React.useEffect(() => {
    fetch("/user")
      .then(response => {
         if (response.status !== 401) {
           setLoadingUser(false);
           setIsLoggedIn(true);
         } else {
           setLoadingUser(false);
         }
       })
  }, []);

  if (loadingUser) {
    return <div>Loading...</div>
  }

  function onLogin(){
    setIsLoggedIn(true)
  }

  function onLogut() {
    setIsLoggedIn(false);
    }


  if (!isLoggedIn) {
      return (
            <div className="App">
                <Login onLogin={onLogin} />
                <BrowserRouter>
                  <Link to='/korisnici'>registracija</Link>
                </BrowserRouter>
            </div>
    )
  }

  return (
    <BrowserRouter>
      <Header onLogout={onLogut}/>
      <div className="App">
              <Switch>
                  <Route path='/' exact component={Login} />
                  <Route path='/korisnici' exact component={ClientForm} />
                  <Route path='/staff' exact component={StaffForm} />
                  <Route path='/requests' exact component={RequestList}/>
            </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;
