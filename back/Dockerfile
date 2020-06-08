FROM mcr.microsoft.com/dotnet/core/sdk:3.1 AS build
WORKDIR /src
COPY ["ItisCovidApi/", "ItisCovidApiBuild/"]
WORKDIR "/src/ItisCovidApiBuild"
RUN dotnet restore 
COPY . .
RUN dotnet publish "ItisCovidApi.Web/ItisCovidApi.Web.csproj" -c release -o /app --no-restore

FROM mcr.microsoft.com/dotnet/core/aspnet:3.1 AS base
WORKDIR /app
EXPOSE 80
COPY --from=build /app .
ENTRYPOINT ["dotnet", "ItisCovidApi.Web.dll"]