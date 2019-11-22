import React from "react";
import "./ClientForm.css"
import Card from "./Card";
import Login from "./Login";

function ClientForm(props) {
    const [form, setForm] = React.useState({ username: '', lozinka: '', givenname: '', familyname: ''});

  function onChange(event) {
    const {name, value} = event.target;
    setForm(oldForm => ({...oldForm, [name]: value}))
  }

  function onSubmit(e) {
      e.preventDefault();

      const data = {
          username: form.username,
          lozinka: form.lozinka,
          givenname: form.givenname,
          familyname: form.familyname
      };

      const options = {
          method: 'POST',
          headers: {
             'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
      };

    return fetch('/korisnici', options)
      .then(response => {
        if (response.ok) {
            props.history.push('/korisnici');

        }
      });
  }

  function isValid() {
    const {username, givenname, familyname, lozinka} = form;
    return givenname.length > 0 && familyname.length > 0 && lozinka.length > 0;
  }

  return (
    <Card title="Registracija">
      <div className="ClientForm">
        <form onSubmit={onSubmit}>
          <div className="FormRow">
            <label>Korisničko ime</label>
            <input type="text" name='username' onChange={onChange} value={form.username}/>
          </div>
          <div className="FormRow">
            <label>Ime</label>
            <input type="text" name='givenname' onChange={onChange} value={form.givenname}/>
          </div>
          <div className="FormRow">
            <label>Prezime</label>
            <input type="text" name='familyname' onChange={onChange} value={form.familyname}/>
          </div>
          <div className="FormRow">
            <label>Lozinka</label>
            <input  type="password" name='lozinka' onChange={onChange} value={form.lozinka}/>
          </div>
          <button type="submit" disabled={!isValid()}>Registriraj se</button>
        </form>
      </div>
    </Card>
  )
}

export default ClientForm;
