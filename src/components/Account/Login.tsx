import { useState } from "react";
import { Link } from "react-router-dom";
import axios from 'axios'
import {BACKEND_URL} from '../../constants.ts'

export const Login = () => {

  const [email, setEmail] = useState('');
  const [passwd, setPasswd] = useState('');


  const handleLogin = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log("Email:", email);
    console.log("Password:", passwd);
  
    axios.post(BACKEND_URL+'/login', {
      email: email,
      password: passwd
    })
    .then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
  };
  
  return (
    <>
        <h1 className="text-[3rem] font-bold">Destination Bucket List</h1>
        <form className="flex flex-col [&>*]:m-6 mt-[10rem]" onSubmit={handleLogin}>
            <div>
              <input 
                id="email" 
                type="text" 
                placeholder='Email'
                name="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)} 
                className="outline outline-4 p-2 outline-[rgb(243,236,224)] rounded-xl h-10" />
            </div>
            <div>
              <input 
                id="password" 
                type="password" 
                placeholder='Password'
                name="password"
                value={passwd}
                onChange={(e) => setPasswd(e.target.value)}
                className="outline outline-4 p-2 outline-[rgb(243,236,224)] rounded-xl h-10" />
            </div>
            <div><button type="submit" className="transition duration-300 ease-out rounded-full w-24 h-12 bg-indigo-600 hover:bg-indigo-800 shadow-[0px_10px_0px_rgba(79,70,229,0.25)] hover:shadow-[0px_10px_0px_rgba(50,37,95,0.65)] hover:drop-shadow-[0_0px_5px_rgba(79,70,229,0.75)] font-bold">Login</button></div>
        </form>
        Don't have an account?&nbsp;
        <Link to="/register" className="text-blue-500 hover:underline">
            Register here.
        </Link>
        <div className="flex flex-row justify-center mt-6">
          <svg className="" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="28" height="28"><path fill="none" d="M0 0h24v24H0z"></path><path fill="currentColor" d="M7.828 11H20v2H7.828l5.364 5.364-1.414 1.414L4 12l7.778-7.778 1.414 1.414z"></path></svg>
          <Link to="/">
            <p className="text-lg hover:underline">&nbsp;Back</p>
          </Link>
        </div>
    </>
  )
}

