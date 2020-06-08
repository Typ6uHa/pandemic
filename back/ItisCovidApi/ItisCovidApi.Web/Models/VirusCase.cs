using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace ItisCovidApi.Web.Models
{
    public class VirusCase
    {
        public int Id { get; set; }
        [Required]
        public int Age { get; set; }
        [Required]
        public string Symptoms { get; set; }

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
        public int CityId { get; set; }
    }
}
