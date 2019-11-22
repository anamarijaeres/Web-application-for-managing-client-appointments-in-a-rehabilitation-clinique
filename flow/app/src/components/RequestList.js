import React from 'react';
import Request from './Request';
import './RequestList.css';
import Card from "./Card";

function RequestList() {
  const [requests, setRequests] = React.useState([]);

  React.useEffect(() => {
    fetch('/requests')
      .then(data => data.json())
      .then(requests => setRequests(requests))
  }, []);

  return (
    <Card title="Zahtjevi za registraciju">
      {
        requests.map(request =>
          <Request key={request.username}
                   request={request}/>
        )
      }
    </Card>
  );
}

export default RequestList;
