using System.Collections.Generic;
using System.IO;
using System.Linq;
using ItisCovidApi.Web.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace ItisCovidApi.Web.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CityController : ControllerBase
    {
        private ApplicationDbContext context;
        public CityController(ApplicationDbContext context)
        {
            this.context = context;
        }

        [HttpGet]
        public OperationResult<List<City>> Get()
        {
            //using (StreamReader r = new StreamReader(@"c:\Users\stwer1998\Downloads\city.json"))
            //{
            //    string json = r.ReadToEnd();
            //    List<Class1> items = JsonConvert.DeserializeObject<List<Class1>>(json);
            //    var arr = new List<City>();
            //    arr = items.Select(x => new City()
            //    {
            //        Name = x.Город,
            //        District = x.Федеральныйокруг,
            //        Latitude = x.Широта,
            //        Longitude = x.Долгота
            //    }).ToList();

            //    context.Cities.AddRange(arr);
            //    context.SaveChanges();

            //}
            return OperationResult.Ok(context.Cities.ToList());
        }

        // GET api/<VirusCaseController>/5
        [HttpGet("{id}")]
        public OperationResult<City> Get(int id)
        {
            return OperationResult.Ok(context.Cities.FirstOrDefault(x => x.Id == id));
        }

        

    }
}
