import React from 'react';
  import {Link} from "react-router-dom";
  import "./Header.css";

function Header(props) {

  function logout() {
    fetch("/logout").then(() => {
      props.onLogout();
    });
  }

  return (
    <header className="Header">
      <Link to='/korisnici'>Klijent</Link>
      <Link to='/staff'>Zaposlenici</Link>
      <Link to='/requests'>Zahtjevi</Link>
      <button onClick={logout}>Odjava</button>
    </header>
  )
}

export default Header;
