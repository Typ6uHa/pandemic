using System.Collections.Generic;
using System.Linq;
using ItisCovidApi.Web.Models;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace ItisCovidApi.Web.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class HelpTaskController : ControllerBase
    {
        private ApplicationDbContext context;
        public HelpTaskController(ApplicationDbContext context)
        {
            this.context = context;
        }

        // GET: api/<HelpTaskController>
        [HttpGet]
        public OperationResult<List<HelpTask>> Get()
        {
            return OperationResult.Ok(context.HelpTasks.Where(x=>x.Status==HelpTaskStatus.Open).ToList());
        }

        // GET api/<HelpTaskController>/5
        [HttpGet("{id}")]
        public OperationResult<HelpTask> Get(int id)
        {
            return OperationResult.Ok(context.HelpTasks.FirstOrDefault(x => x.id==id));
        }

        [HttpGet("/user/{PhoneSerialNumber}")]
        public OperationResult<HelpTask> GetUserOpentask(string PhoneSerialNumber) 
        {
            return OperationResult.Ok(context.HelpTasks
                .FirstOrDefault(x => x.PhoneSerialNumber == PhoneSerialNumber&&x.Status==HelpTaskStatus.Open));
        }

        // POST api/<HelpTaskController>
        [HttpPost]
        public OperationResult<HelpTask> Post([FromBody] HelpTask value)
        {
            if (ModelState.IsValid)
            {
                var oldtask = context.HelpTasks.FirstOrDefault(x => x.PhoneSerialNumber == value.PhoneSerialNumber
                  && x.Status == HelpTaskStatus.Open);
                if (oldtask == null)
                {
                    value.Status = HelpTaskStatus.Open;
                    var result = context.HelpTasks.Add(value);
                    context.SaveChanges();
                    return OperationResult.Ok(result.Entity);
                }
                else 
                {
                    return OperationResult.Fail<HelpTask>("У пользователя уже есть запрос");
                }
            }
            else
                return OperationResult.Fail<HelpTask>("Модель не валидный");
        }

        [HttpPost("{id}/close")]
        public OperationResult<HelpTask> CloseTask(int id)
        {
            var result = context.HelpTasks.FirstOrDefault(x => x.id == id);
            if (result != null) 
            {
                result.Status = HelpTaskStatus.Closed;
                context.Update(result);
                context.SaveChanges();
                return OperationResult.Ok(result);
            }
            else
                return OperationResult.Fail<HelpTask>("нет токого запроса");
        }

        [HttpGet("city/{id}")]
        public OperationResult<List<HelpTask>> GetCitycase(int id)
        {
            return OperationResult.Ok(context.HelpTasks.Where(x => x.CityId == id&&x.Status==HelpTaskStatus.Open).ToList());
        }

        [HttpGet("city")]
        public OperationResult<List<CityCount>> Getcitycount()
        {
            var a = context.HelpTasks
                .Where(x=>x.Status==HelpTaskStatus.Open)
                .GroupBy(x => x.CityId)
                .Select(x => new CityCount { IdCity = x.Key, Count = x.Count() })
                .ToList();
            return OperationResult.Ok(a);
        }
    }
}
