import React from "react";
import axios from 'axios';
import {Card} from "react-bootstrap";

class DeleteStudentFromProject extends React.Component{
    constructor(props) {
        super(props)
        const queryParams = new URLSearchParams(window.location.search);
        this.state = {
            projectId:queryParams.get('projectId'),
            studentId:queryParams.get('studentId'),
        };
    }


    componentDidMount() {

        axios({
            method:'delete',
            url:'http://localhost:8080/deleteStudentFromProject?projectId='+this.state.projectId
                +'&studentId='+this.state.studentId,
        }).then(()=>{


                window.location = "/lecturer/projects/studentList?projectId="+this.state.projectId;
            }
        )

    }

    render() {
        return (
            <Card className={"border border-dark bg-dark text-white"}>
            </Card>
        );
    }
}

export default DeleteStudentFromProject;