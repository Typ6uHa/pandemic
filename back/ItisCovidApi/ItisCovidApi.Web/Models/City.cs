using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ItisCovidApi.Web.Models
{
    public class City
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string District { get; set; }

        /// <summary>
        /// Широта
        /// </summary>
        public string Latitude { get; set; }

        /// <summary>
        /// Долгота
        /// </summary>
        public string Longitude { get; set; }
    }


    public class Rootobject
    {
        public Class1[] Property1 { get; set; }
    }

    public class Class1
    {
        public string Индекс { get; set; }
        public string Типрегиона { get; set; }
        public string Регион { get; set; }
        public string Типрайона { get; set; }
        public string Район { get; set; }
        public string Типгорода { get; set; }
        public string Город { get; set; }
        public string Типнп { get; set; }
        public string Нп { get; set; }
        public string КодКЛАДР { get; set; }
        public string КодФИАС { get; set; }
        public string УровеньпоФИАС { get; set; }
        public string Признакцентрарайонаилирегиона { get; set; }
        public string КодОКАТО { get; set; }
        public string КодОКТМО { get; set; }
        public string КодИФНС { get; set; }
        public string Часовойпояс { get; set; }
        public string Широта { get; set; }
        public string Долгота { get; set; }
        public string Федеральныйокруг { get; set; }
        public string Население { get; set; }
    }

}
