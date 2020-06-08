using Microsoft.EntityFrameworkCore.ChangeTracking;
using System;
using System.Diagnostics.CodeAnalysis;
using System.Diagnostics.Contracts;

namespace ItisCovidApi.Web.Models
{
    public class OperationResult
    {
        public bool Success { get; private set; }
        public string Error { get; private set; }

        public bool Failure
        {
            get { return !Success; }
        }

        protected OperationResult(bool success, string error)
        {
            Contract.Requires(success || !string.IsNullOrEmpty(error));
            Contract.Requires(!success || string.IsNullOrEmpty(error));

            Success = success;
            Error = error;
        }

        public static OperationResult Fail(string message)
        {
            return new OperationResult(false, message);
        }

        public static OperationResult<T> Fail<T>(string message)
        {
            return new OperationResult<T>(default(T), false, message);
        }

        public static OperationResult Ok()
        {
            return new OperationResult(true, String.Empty);
        }

        public static OperationResult<T> Ok<T>(T value)
        {
            return new OperationResult<T>(value, true, String.Empty);
        }

        public static OperationResult Combine(params OperationResult[] results)
        {
            foreach (OperationResult result in results)
            {
                if (result.Failure)
                    return result;
            }

            return Ok();
        }
    }

    public class OperationResult<T> : OperationResult
    {
        private T _value;

        public T Value
        {
            get
            {
                Contract.Requires(Success);
                return _value;
            }
            [param: AllowNull]
            private set { _value = value; }
        }

        protected internal OperationResult(T value, bool success, string error)
            : base(success, error)
        {
            Contract.Requires(value != null || !success);

            Value = value;
        }
    }
}
