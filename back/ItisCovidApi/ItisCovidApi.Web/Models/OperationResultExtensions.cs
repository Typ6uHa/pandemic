using System;

namespace ItisCovidApi.Web.Models
{
    public static class OperationResultExtensions
    {
        public static OperationResult OnSuccess(this OperationResult result, Func<OperationResult> func)
        {
            if (result.Failure)
                return result;

            return func();
        }

        public static OperationResult OnSuccess(this OperationResult result, Action action)
        {
            if (result.Failure)
                return result;

            action();

            return OperationResult.Ok();
        }

        public static OperationResult OnSuccess<T>(this OperationResult<T> result, Action<T> action)
        {
            if (result.Failure)
                return result;

            action(result.Value);

            return OperationResult.Ok();
        }

        public static OperationResult<T> OnSuccess<T>(this OperationResult result, Func<T> func)
        {
            if (result.Failure)
                return OperationResult.Fail<T>(result.Error);

            return OperationResult.Ok(func());
        }

        public static OperationResult<T> OnSuccess<T>(this OperationResult result, Func<OperationResult<T>> func)
        {
            if (result.Failure)
                return OperationResult.Fail<T>(result.Error);

            return func();
        }

        public static OperationResult OnSuccess<T>(this OperationResult<T> result, Func<T, OperationResult> func)
        {
            if (result.Failure)
                return result;

            return func(result.Value);
        }

        public static OperationResult OnFailure(this OperationResult result, Action action)
        {
            if (result.Failure)
            {
                action();
            }

            return result;
        }

        public static OperationResult OnBoth(this OperationResult result, Action<OperationResult> action)
        {
            action(result);

            return result;
        }

        public static OperationResult OnBoth(this OperationResult result, Action action)
        {
            action();

            return result;
        }

        public static T OnBoth<T>(this OperationResult result, Func<OperationResult, T> func)
        {
            return func(result);
        }

        public static OperationResult OnBoth<T>(this OperationResult result, Action action)
        {
            action();
            return result;
        }

        public static OperationResult<bool> OnTrue(this OperationResult<bool> result, Action action)
        {
            if (result.Value == true)
            {
                action();
            }
            return result;
        }

        public static OperationResult<bool> OnFalse(this OperationResult<bool> result, Action action)
        {
            if (result.Value == false)
            {
                action();
            }
            return result;
        }
    }
}
