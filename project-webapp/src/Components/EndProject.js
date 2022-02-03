import React from "react";
import axios from 'axios';
import {Button, Card, Col, Form} from "react-bootstrap";

class EndProject extends React.Component{
    constructor(props) {
        super(props)
        const queryParams = new URLSearchParams(window.location.search);
        this.state = {
            projectId:queryParams.get('projectId'),
        };
    }


    componentDidMount() {

        axios({
            method:'put',
            url:'http://localhost:8080/endProject?projectId='+this.state.projectId,
        }).then(()=>{
                let user ='';
                if ('STUDENT' === localStorage.getItem('typeOfUser')) {
                    user = 'student';

                }else{
                    user = 'lecturer';

                }

                window.location = "/"+user+"/projects";
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

export default EndProject;