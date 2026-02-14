interface Props {
  pendingCount: number;
  total: number;
}

export default function TodoStats({ pendingCount, total }: Props) {
  if (total === 0) return null;
  return (
    <p className="text-sm text-gray-500 mb-4">
      {pendingCount} task{pendingCount !== 1 ? "s" : ""} remaining
    </p>
  );
}
