import { useState } from "react";
import { Link } from "react-router-dom";
import axios from 'axios'


export const Login = () => {

  const [email, setEmail] = useState('');
  const [passwd, setPasswd] = useState('');


  const handleLogin = async (e: React.FormEvent<HTMLFormElement>) => {

    e.preventDefault();
    console.log("Email:", email);
    console.log("Password:", passwd);

    // try {
    //   const response = await axios.post("http://localhost:8080/login", {
    //     email: email,
    //     password: passwd,
    //   });

    //   // Login successful, handle the response
    //   console.log("Login successful");
    //   // Redirect to a different page or perform additional actions
    // } catch (error) {
    //   // Login failed, handle the error
    //   console.log("Login failed");
    // }


  };


  return (
    <>
        <form className="flex flex-col" onSubmit={handleLogin}>
            <div>
              <input 
                id="email" 
                type="text" 
                placeholder='Email'
                name="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}  />
            </div>
            <div>
              <input 
                id="password" 
                type="password" 
                placeholder='Password'
                name="password"
                value={passwd}
                onChange={(e) => setPasswd(e.target.value)} />
            </div>
            <div><button type="submit" className="text-2xl">Login</button></div>
        </form>
        Don't have an account?&nbsp;
        <Link to="/register">
            Register here.
        </Link>
        <div>
          <Link to="/">
              Back
          </Link>
        </div>
    </>
  )
}

