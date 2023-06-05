import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";

export const AdminHome = () => {
  
    const [index, setIndex] = useState(0);
    const [destinations, setDestinations] = useState<Destination[]>([]);
    
    async function populateDestinations() {

        const updatedDestinations: Destination[] = [];
        
        updatedDestinations.push({
          title: "Madrid",
          geolocation: "111.222, -33.444",
          image: "newdestination.jpg",
          description: "This is the description for the new destination.",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Cluj-Napoca",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Prague",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Munchen",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Milano",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Roma",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Napoli",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Stockholm",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "London",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Budapest",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Bucharest",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Lisbon",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Torino",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Barcelona",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Valencia",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });
      
        updatedDestinations.push({
          title: "Frankfurt",
          geolocation: "",
          image: "",
          description: "miauritius",
          startDate: new Date,
          endDate: new Date
        });

        const promises = updatedDestinations.map(async (destination) => {
            const geolocation = await getGeolocation(destination.title);
            const image = await getCityImage(destination.title);
            const description = await getCityDescription(destination.title);
        
            return {
              ...destination,
              geolocation,
              image,
              description,
            };
          });

        const resolvedDestinations = await Promise.all(promises);
        setDestinations(resolvedDestinations);
    }

    populateDestinations();

    console.log(destinations);
  
  async function getGeolocation(destinationTitle: string): Promise<string | null> {
  try {
    const encodedDestination = encodeURIComponent(destinationTitle);

    const response = await axios.get(
      `https://geocode.maps.co/search?q=${encodedDestination}`
    );

    if (response.data.length > 0) {
      const { lat, lon } = response.data[0];
      return `${lat}, ${lon}`;
    } else {
      return null; // Geolocation not found for the destination
    }
  } catch (error) {
    console.error("Error retrieving geolocation:", error);
    return null;
  }
}


  // pixabay api key: 36998343-211d018d1523f85df7020a1f6

  async function getCityImage(cityName: string): Promise<string | null> {
    try {
      const apiKey = "36998343-211d018d1523f85df7020a1f6"; // Replace with your own Pixabay API key
      const encodedCityName = encodeURIComponent(cityName);

      const response = await axios.get(
        `https://pixabay.com/api/?key=${apiKey}&q=${encodedCityName}&category=places`
      );

      const { hits } = response.data;

      if (hits.length > 0) {
        const randomIndex = Math.floor(Math.random() * hits.length);
        const imageUrl = hits[randomIndex].webformatURL;
        return imageUrl;
      } else {
        return null; // No image found for the city
      }
    } catch (error) {
      console.error("Error retrieving city image:", error);
      return null;
    }
  }

  function cutLastWord(str: string): string {
    const words = str.trim().split(" ");
    words.pop(); // Remove the last word from the array
    return words.join(" ");
  }

  async function getCityDescription(cityName: string): Promise<string | null> {
    try {
      const response = await axios.get(
        `http://api.geonames.org/wikipediaSearchJSON?q=${encodeURIComponent(cityName)}&maxRows=1&username=callisto8008`
      );
  
      const { geonames } = response.data;
  
      if (geonames && geonames.length > 0) {
        const description = geonames[0].summary;
        const shortDescription = description.substring(0, 120);
        const actualDescription = cutLastWord(shortDescription) + "...";
        return actualDescription;
      } else {
        return null; 
      }
    } catch (error) {
      console.error("Error retrieving city description:", error);
      return null;
    }
  }
  

  // Accessing updatedDestinations
  destinations.forEach((destination) => {
    console.log("Title:", destination.title);
    console.log("Geolocation:", destination.geolocation);
    console.log("Image:", destination.image);
    console.log("Description:", destination.description);
    getGeolocation(destination.title).then((geolocation) => {
      if (geolocation) {
        destination.geolocation=geolocation;
        console.log(`Geolocation for ${destination.title}: ${destination.geolocation}`);
      } else {
        console.log(`Geolocation not found for ${destination.title}`);
      }
    });
    getCityImage(destination.title).then((imageUrl) => {
      if (imageUrl) {
        destination.image=imageUrl;
        console.log(`Image URL for ${destination.title}: ${imageUrl}`);
      } else {
        console.log(`No image found for ${destination.title}`);
      }
    });
    getCityDescription(destination.title).then((description) => {
      if (description) {
        destination.description=description;
        console.log(`Description for ${destination.title}: ${description}`);
      } else {
        console.log(`Description not found for ${destination.title}`);
      }
    });
  });

  return (
    <>
        <div>
            <img src={destinations[index].image || ""} alt="" />
            <h1>{destinations[index].title}</h1>
            <p>{destinations[index].description}</p>
            <p>{destinations[index].geolocation}</p>
        </div>
        <div className="grid grid-cols-4">
            {destinations.map((dest: Destination) => (
                <p>{dest.title}</p>
            ) )}
        </div>
    </>
  );
};
