import React from "react";

function Student(props) {
  const {jmbag, givenName, familyName} = props.student;

  return (
    <p>{givenName} {familyName} ({jmbag})</p>
  );
}

export default Student;
