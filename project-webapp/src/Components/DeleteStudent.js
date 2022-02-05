import React from "react";
import axios from 'axios';
import {Card} from "react-bootstrap";

class EditKanbanDoing extends React.Component{
    constructor(props) {
        super(props)
        const queryParams = new URLSearchParams(window.location.search);
        this.state = {
            studentId:queryParams.get('studentId'),
            loginId:queryParams.get('loginId'),
        };
    }


    componentDidMount() {

        axios({
            method:'delete',
            url:'http://localhost:8080/deleteStudent/'+this.state.studentId+'?loginId='+this.state.loginId,
        }).then(()=>{

                window.location = "/lecturer/studentList";
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

export default EditKanbanDoing;