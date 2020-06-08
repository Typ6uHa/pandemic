using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
using ItisCovidApi.Web.Models;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace ItisCovidApi.Web.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class VirusCaseController : ControllerBase
    {
        private ApplicationDbContext context;
        public VirusCaseController(ApplicationDbContext context)
        {
            this.context = context;
        }

        // GET: api/<VirusCaseController>
        [HttpGet]
        public OperationResult<List<VirusCase>> Get()
        {
            return OperationResult.Ok(context.VirusCases.ToList());
        }

        // GET api/<VirusCaseController>/5
        [HttpGet("{id}")]
        public OperationResult<VirusCase> Get(int id)
        {
            var result = context.VirusCases.FirstOrDefault(x => x.Id == id);
            if (result != null)
            {
                return OperationResult.Ok(result);
            }
            return OperationResult.Fail<VirusCase>("Нет токой случай");
        }

        // POST api/<VirusCaseController>
        [HttpPost]
        public OperationResult<VirusCase> Post([FromBody] VirusCase value)
        {
            if (ModelState.IsValid)
            {
                var result = context.VirusCases.Add(value);
                context.SaveChanges();
                return OperationResult.Ok(result.Entity);
            }
            return OperationResult.Fail<VirusCase>("Модель не валидный");
        }

        

        [HttpGet("city/{id}")]
        public OperationResult<List<VirusCase>> GetCitycase(int id) 
        {
            return OperationResult.Ok(context.VirusCases.Where(x=>x.CityId==id).ToList());
        }

        [HttpGet("city")]
        public OperationResult<List<CityCount>> Getcitycount() 
        {
            var result = context.VirusCases
                .GroupBy(x => x.CityId)
                .Select(x => new CityCount{ IdCity=x.Key,Count=x.Count() }).ToList();
            return OperationResult.Ok(result);
        }

    }
}
