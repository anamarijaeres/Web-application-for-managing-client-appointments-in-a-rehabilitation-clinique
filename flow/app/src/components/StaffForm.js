import React from "react";
import "./StaffForm.css"
import Card from "./Card";

function StaffForm(props) {
  const [form, setForm] = React.useState({username: '', firstName: '', lastName: '', email: '', associateNum: '', password: ''});

  function onChange(event) {
    const {name, value} = event.target;
    setForm(oldForm => ({...oldForm, [name]: value}))
  }

  function onSubmit(e) {
    e.preventDefault();
    const data = {
      username: form.username,
      firstName: form.firstName,
      lastName: form.lastName,
      password: form.password,
      email: form.email,
      associateNum: form.associateNum
    };
    const options = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    };

    return fetch('/students', options)
      .then(response => {
        if (response.ok) {
          props.history.push('/student');
        }
      });
  }

  function isValid() {
    const {username, firstName, lastName, email, associateNum, password} = form;
    return firstName.length > 0 && lastName.length > 0 && email.length > 0 && associateNum > 0 && password.length > 0;
  }

  return (
    <Card title="Registracija">
      <div className="StaffForm">
        <form onSubmit={onSubmit}>
          <div className="FormRow">
            <label>Korisničko ime</label>
            <input type="text" name='username' onChange={onChange} value={form.username}/>
          </div>
          <div className="FormRow">
            <label>Ime</label>
            <input type= "text" name='firstName' onChange={onChange} value={form.firstName}/>
          </div>
          <div className="FormRow">
            <label>Prezime</label>
            <input type="text" name='lastName' onChange={onChange} value={form.lastName}/>
          </div>
          <div className="FormRow">
            <label>E-mail</label>
            <input type="text" name='email' onChange={onChange} value={form.email}/>
          </div>
          <div className="FormRow">
            <label>Max. broj suradnika</label>
            <input type="number" name='associateNum' onChange={onChange} value={form.associateNum}/>
          </div>
          <div className="FormRow">
            <label>Lozinka</label>
            <input  type="password" name='password' onChange={onChange} value={form.password}/>
          </div>
          <button type="submit" disabled={!isValid()}>Registriraj se</button>
        </form>
      </div>
    </Card>
  )
}

export default StaffForm;
