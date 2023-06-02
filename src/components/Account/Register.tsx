import { useState } from "react";
import { Link } from "react-router-dom";


export const Register = () => {

  const [email, setEmail] = useState('');
  const [passwd, setPasswd] = useState('');


  const validateRegister = async () => {
    
    

    console.log()
  };


  return (
    <>
        <h1 className="text-[3rem] font-bold">Destination Bucket List</h1>
        <form className="flex flex-col [&>*]:m-6 mt-[10rem]" onSubmit={validateRegister}>
            <div>
              <input 
                id="email" 
                type="text" 
                placeholder='Email'
                value={email}
                onChange={(e) => setEmail(e.target.value)}  
                className="outline outline-4 outline-[rgb(243,236,224)] rounded-xl h-10" />
            </div>
            <div>
              <input 
                id="password" 
                type="password" 
                placeholder='Password'
                value={passwd}
                onChange={(e) => setPasswd(e.target.value)} 
                className="outline outline-4 outline-[rgb(243,236,224)] rounded-xl h-10" />
            </div>
            <div><button type="submit" className="transition duration-300 ease-out rounded-full w-28 h-12 bg-indigo-600 hover:bg-indigo-800 shadow-[0px_8px_0px_rgba(79,70,229,0.25)] hover:shadow-[0px_8px_0px_rgba(50,37,95,0.65)] font-bold">Register</button></div>
        </form>
        <div className="flex flex-row justify-center mt-6">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="28" height="28"><path fill="none" d="M0 0h24v24H0z"></path><path fill="currentColor" d="M7.828 11H20v2H7.828l5.364 5.364-1.414 1.414L4 12l7.778-7.778 1.414 1.414z"></path></svg>
          <Link to="/">
            <p className="text-lg hover:underline">&nbsp;Back</p>
          </Link>
        </div>
        
    </>
  )
}

