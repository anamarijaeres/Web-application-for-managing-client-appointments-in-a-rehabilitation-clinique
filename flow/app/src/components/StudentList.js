import React from 'react';
import Student from './Student';
import './StudentList.css';
import Card from "./Card";

function StudentList() {
  const [students, setStudents] = React.useState([]);

  React.useEffect(() => {
    fetch('/students')
      .then(data => data.json())
      .then(students => setStudents(students))
  }, []);

  return (
    <Card title="Students">
      {
        students.map(student =>
          <Student key={student.jmbag}
                   student={student}/>
        )
      }
    </Card>
  );
}

export default StudentList;
