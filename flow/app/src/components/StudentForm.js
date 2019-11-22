import React from "react";
import "./StudentForm.css"
import Card from "./Card";

function StudentForm(props) {
  const [form, setForm] = React.useState({username: '', givenName: '', familyName: ''});

  function onChange(event) {
    const {name, value} = event.target;
    setForm(oldForm => ({...oldForm, [name]: value}))
  }

  function onSubmit(e) {
    e.preventDefault();
    const data = {
      username: form.username,
      familyName: form.familyName,
      givenName: form.givenName
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
          props.history.push('/students');
        }
      });
  }

  function isValid() {
    const {username, givenName, familyName} = form;
    return username.length === 10 && givenName.length > 0 && familyName.length > 0;
  }

  return (
    <Card title="Registracija">
      <div className="StudentForm">
        <form onSubmit={onSubmit}>
          <div className="FormRow">
            <label>Korisničko ime</label>
            <input name='username' onChange={onChange} value={form.username}/>
          </div>
          <div className="FormRow">
            <label>Ime</label>
            <input name='givenName' onChange={onChange} value={form.givenName}/>
          </div>
          <div className="FormRow">
            <label>Prezime</label>
            <input name='familyName' onChange={onChange} value={form.familyName}/>
          </div>
          <div className="FormRow">
            <label>Lozinka</label>
            <input name='familyName' onChange={onChange} value={form.familyName}/>
          </div>
                  <button type="submit" disabled={!isValid()}>Registriraj se</button>
        </form>
      </div>
    </Card>
  )
}

export default StudentForm;
