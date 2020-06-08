using ItisCovidApi.Web.Models;
using Microsoft.EntityFrameworkCore;

namespace ItisCovidApi.Web
{
    public class ApplicationDbContext : DbContext
    {
        public DbSet<HelpTask> HelpTasks { get; set; }

        public DbSet<VirusCase> VirusCases { get; set; }
        public DbSet<City> Cities { get; set; }

        public ApplicationDbContext(DbContextOptions options) : base(options)
        {
            Database.EnsureCreated();
        }
    }
}