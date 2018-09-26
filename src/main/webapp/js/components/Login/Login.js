import React from 'react'
import PropTypes from 'prop-types'
import { Panel, Button, Checkbox, FormControl } from 'react-bootstrap'

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.inputs = {}
    }

    getInputRef = (ref) => {
        if (ref)
            this.inputs[ref.name] = ref
    };

    submitHandler(e) {
        e.preventDefault();

        let authData = ['username','password'].reduce((data, attr) => Object.assign(data, {[attr]: this.inputs[attr].value.trim()}), {});
        console.log(authData);
        this.props.authLogin(authData);
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-4 col-md-offset-4">
                        <Panel className="login-panel" bsStyle="default">
                            <Panel.Heading>
                                <Panel.Title>Please Sign In</Panel.Title>
                            </Panel.Heading>
                            <Panel.Body>
                                <form role="form" onSubmit={::this.submitHandler}>
                                    <fieldset>
                                        <div className="form-group">
                                            <FormControl className="form-control" placeholder="User" type="text" name="username" inputRef={this.getInputRef}/>
                                        </div>
                                        <div className="form-group">
                                            <FormControl className="form-control" placeholder="Password" type="password" name="password" inputRef={this.getInputRef}/>
                                        </div>
                                        <Checkbox label="Remember Me"> Remember Me </Checkbox>
                                        <Button type="submit" bsSize="large" bsStyle="success" block>Login</Button>
                                    </fieldset>
                                </form>
                            </Panel.Body>
                        </Panel>
                    </div>
                </div>
            </div>
        )
    }
}

Login.propTypes = {
    authLogin: PropTypes.func.isRequired
};

export default Login