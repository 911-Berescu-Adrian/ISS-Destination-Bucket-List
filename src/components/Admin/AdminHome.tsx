import axios from "axios";
import { useEffect, useState } from "react";
import { BACKEND_URL } from "../../constants";
import { Link } from "react-router-dom";
import { AdminAdd } from "./AdminAdd";

export const AdminHome = () => {
  
    const [index, setIndex] = useState(0);
    const [destinations, setDestinations] = useState<Destination[]>([]);
    

    useEffect(() => {
      // Fetch destinations from API
      axios
        .get(BACKEND_URL + '/destination/getAll')
        .then(response => {
          console.log(response.data);
          const fetchedDestinations = response.data.map((destination: Destination) => ({
            title: destination.title,
            geolocation: destination.geolocation || null,
            image: destination.image || null,
            description: destination.description || null,
            startDate: new Date(destination.startDate),
            endDate: new Date(destination.endDate)
          }));
          setDestinations(fetchedDestinations);
        })
        .catch(error => {
          console.log("Error fetching destinations:", error);
        });
    }, []);
    
    

  console.log(destinations);
  console.log(index);

  const handleGridClick = (gridIndex: number) => {
    setIndex(gridIndex);
  };
  
  return (
    <>
        <center>
          <div>
              <img src={destinations[index].image}  alt="" />
              <h1>{destinations[index].title}</h1>
              <p>{destinations[index].description}</p>
              <p>{destinations[index].geolocation}</p>
          </div>
          <div className="flex flex-row justify-center space-x-8">
            <Link to={'/admin/add'}>
            <button className="bg-[rgb(6,78,59,0.35)] hover:bg-[rgb(6,78,59,0.45)] mt-8 w-44 h-12 text-emerald-400 rounded-lg font-bold">Add new destination</button>
            </Link>

            <Link to={'/admin/update'}>
            <button className="bg-[rgb(136,19,55,0.35)] hover:bg-[rgb(136,19,55,0.45)] mt-8 w-44 h-12 text-rose-400 rounded-lg font-bold">Remove destination</button>
            </Link>

            <Link to={'/admin/add'}>
            <button className="bg-[rgb(49,46,129,0.35)] hover:bg-[rgb(69,66,149,0.45)] mt-8 w-44 h-12 text-indigo-400 rounded-lg font-bold">Update destination</button>
            </Link>
          </div>
        </center>
        <div className="grid grid-cols-4 mt-12">
            {destinations.map((dest: Destination, gridIndex ) => (
                <p key={dest.title} onClick={() => handleGridClick(gridIndex)} className="hover:cursor-pointer">{dest.title}</p>
            ) )}
        </div>
    </>
  );
};
