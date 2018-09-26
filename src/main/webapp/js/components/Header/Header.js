import React from 'react';
import Navbar, {Brand} from 'react-bootstrap/lib/Navbar';
import Sidebar from '../Sidebar/Sidebar';
import {ProfileMenu} from '../../containers/Profile';

function Header(props) {
  return (
    <div id="wrapper" className="content">
      <Navbar fluid={true}  style={ {margin: 0} }>
          <Brand>
            <span>
              <span>&nbsp;Weather viewer </span>
            </span>
          </Brand>
          <ul className="nav navbar-top-links navbar-right">
              {props.isAuth && <ProfileMenu/>}
          </ul>
          <Sidebar {...props}/>
    </Navbar>
    </div>
  );
}

export default Header;
