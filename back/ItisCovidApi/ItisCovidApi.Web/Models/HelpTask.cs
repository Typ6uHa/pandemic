using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace ItisCovidApi.Web.Models
{
    public class HelpTask
    {
        public int id { get; set; }
        
        [Required]
        public int CityId { get; set; }
        
        [Required]
        public string PhoneSerialNumber { get; set; }

        [Required]
        public string Adress { get; set; }

        /// <summary>
        /// Широта
        /// </summary>
        [Required] 
        public string Latitude { get; set; }

        /// <summary>
        /// Долгота
        /// </summary>
        [Required] 
        public string Longitude { get; set; }
        [Required]
        public string Description { get; set; }
        [Required]
        public string PhoneNumber { get; set; }
       
        public HelpTaskStatus Status { get; set; }
    }

    public enum HelpTaskStatus 
    {
        Open=0,
        Closed=1
    }
}
