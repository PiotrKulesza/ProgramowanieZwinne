import React from "react";
import axios from 'axios';
import {Card} from "react-bootstrap";

class DeleteProject extends React.Component{
    constructor(props) {
        super(props)
        const queryParams = new URLSearchParams(window.location.search);
        this.state = {
            projectId:queryParams.get('projectId'),
            taskId:queryParams.get('taskId'),
        };
    }


    componentDidMount() {

        axios({
            method:'delete',
            url:'http://localhost:8080/deleteProject?projectId='+this.state.projectId,
        }).then(()=>{
                let user ='';
                if ('STUDENT' === localStorage.getItem('typeOfUser')) {
                    user = 'student';

                }else{
                    user = 'lecturer';

                }

                window.location = "/"+user+"/projects?projectId="+this.state.projectId;
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

export default DeleteProject;