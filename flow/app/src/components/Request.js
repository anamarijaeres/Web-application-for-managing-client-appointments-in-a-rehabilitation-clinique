import React from "react";

function Request(props) {
  const {username, firstName, lastName} = props.request;

  return (
    <p>{username} {firstName} ({lastName})</p>
  );
}

export default Request;
